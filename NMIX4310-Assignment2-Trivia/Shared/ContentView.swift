//
//  ContentView.swift
//  Shared
//
//  Created by Owen Reynolds on 2/15/22.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var triviaList = GetData()
    
    var body: some View {
        NavigationView {
            List(triviaList.trivia) { elem in
                NavigationLink(destination:
                    Text(elem.answer)
                        .font(.title)
                        .padding()
                ) {
                    Text(elem.question)
                        .font(.title)
                        .padding()
                }
            }
            .navigationBarTitle("Random Trivia")
                .font(.title)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

struct DataLayout: Codable, Identifiable {
    public var id: Int
    public var answer: String
    public var question: String
    
    enum CodingKeys: String, CodingKey {
        case id
        case answer
        case question
    }
}

public class GetData: ObservableObject {
    @Published var trivia = [DataLayout]()
    init() {
        load()
    }
    
    func load() {
        let dataUrl = URL(string: "https://jservice.io/api/random?count=10")!
        let decoder = JSONDecoder()
        URLSession.shared.dataTask(with: dataUrl) {(data, response, error) in
            do {
                if let d = data {
                    let decodedLists = try decoder.decode([DataLayout].self, from: d)
                    DispatchQueue.main.async {
                        self.trivia = decodedLists
                    }
                } else {
                    print("No Data")
                }
            } catch {
                print("error")
            }
        }.resume()
    }
}
